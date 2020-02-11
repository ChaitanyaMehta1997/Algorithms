//package Algo;
/*
 * 
 * @Author : Chaitanya Mehta
 * 
 */

import java.util.ArrayList;

public class KthLargest {
	public static int kthLargest(int[] a, int k) {
		/*
		 * 
		 * 5 4 1 1 4 5
		 */

		int Store[] = new int[k];
		int i = 0;

		while (i != k) {
			Store[i] = (a[i]);
			MakeMinHeap(Store,i);
			i += 1;

		}
		

		while (i < a.length) {
			if (a[i] >= Store[0]) {
				Store[0] = a[i];
				bubbledown(Store);
								
			}
			i+=1;

		}
		System.out.println("The Kth largest element is : "+Store[0]);
		return Store[0];

	}
	public static int[] MakeMinHeap(int Store[],int i) {
		int child_index = i;
		
		int temp2=0;
		
		while(true) {
			int child = Store[child_index];
			int parent_index = (int) Math.floor((child_index-1)/2);
			temp2=0;
			boolean flag = true;
			if(parent_index<0) {
				break;
			}
			if(Store[parent_index]>child) {
				temp2 = Store[child_index];
				Store[child_index] = Store[parent_index];
				Store[parent_index] = temp2;
				 child_index = parent_index;
				 flag= false;
				
			}
			if(flag) {
				break;
			}
			
			
		}
		return Store;
		
		
	}
	
	public static int[] bubbledown(int Store[]) {
		/*
		 * First step would be to add the element ekdam last Then we and shift it to its
		 * deserved position 1 2 8 3 4 5 0 1 2 3 4 5 2
		 */
		int parent_index = 0;
		int parent_element = Store[parent_index];
		int leftChild_index=0;
		int RightChild_index =0;
		
		while (leftChild_index < Store.length || RightChild_index < Store.length) {
			int temp = 0;
			leftChild_index = 2 * parent_index + 1;
			RightChild_index = 2 * parent_index + 2;

			if (leftChild_index < Store.length && RightChild_index < Store.length) {
				
				if (Store[leftChild_index] < parent_element && Store[(RightChild_index)] < parent_element) {
					if (Store[leftChild_index] < Store[(RightChild_index)]) {
						temp = Store[leftChild_index];
						Store[leftChild_index] = Store[parent_index];
						Store[parent_index] = temp;
						parent_index = leftChild_index;

					} 
					else if(Store[leftChild_index] == Store[RightChild_index]) {
						temp = Store[leftChild_index];
						Store[leftChild_index] = Store[parent_index];
						Store[parent_index] = temp;
						parent_index = leftChild_index;
					}
					
					else {
						temp = Store[RightChild_index];
						Store[RightChild_index] = Store[parent_index];
						Store[parent_index] = temp;
						parent_index = RightChild_index;

					}

				}
			}
			if (leftChild_index < Store.length && temp ==0) {
				if (Store[leftChild_index] < parent_element) {
					temp = Store[leftChild_index];
					Store[leftChild_index] = Store[parent_index];
					Store[parent_index] = temp;
					parent_index = leftChild_index;

				}
			}
			if (RightChild_index < Store.length && temp ==0) {
				if (Store[RightChild_index] < parent_element) {
					
					temp = Store[RightChild_index];
					Store[RightChild_index] = Store[parent_index];
					Store[parent_index] = temp;
					parent_index = RightChild_index;

				}
			}
			if(temp == 0) {
				break;
			}
		}
		return Store;
		
	}

	public static void main(String args[]) {
			
		int a[] = {5,4,3,2,1,0};
			     //0 1 2 3 4 5 6
						
		int k = 3;
		int z = kthLargest(a, k);
		System.out.println(z);
		

	}

}
