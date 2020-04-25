package com.timkanake.JavaPlayground;


public class LeetCodeLinkedList {
	LLNode head;
    int lLen;
    class LLNode{
        int value;
        LLNode next;
        public LLNode(int v, LLNode n){
            value = v;
            next = n;
        }
        public LLNode(int v){
            value = v;
        }
    }
    /** Initialize your data structure here. */
    public LeetCodeLinkedList() {
    	lLen = 0;
    	head = new LLNode(-1, null);
    }
    
    public void printList() {
    	LLNode headR = head;
    	while(headR != null) {
    		System.out.print(headR.value + ",");
    		headR = headR.next;
    	}
    	System.out.println(" ");
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index >= lLen || index < 0){
            return -1;
        }
        LLNode headR = head;
        for(int i = 0; i < index; i++) {
        	headR = headR.next;
        }
        return headR.next.value;    
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0, val);       
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(lLen, val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
    	if(index < 0 || index > lLen) {
    		return;
    	}
    	LLNode headR = head;
    	for(int i = 0; i < index; i++) {
    		headR = headR.next;
    	}
    	headR.next = new LLNode(val, headR.next);
        lLen++;        
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index >= lLen || index < 0){
            return;
        }
        LLNode headR = head;
        for(int i = 0; i < index; i++){
            headR = headR.next;
        }
        
        LLNode delNode = headR.next;
        headR.next = delNode.next;
        delNode.next = null;
        lLen--;
    }
}
