package Controller;

import Model.Room.RoomType;

class Node{
	RoomType roomType;
	int 	 numberOfRooms;
	Node 	 next;
	
    public Node(RoomType roomType, int numberOfRooms) {
        this.roomType 	   = roomType;
        this.numberOfRooms = numberOfRooms;
        this.next 		   = null;
    }
}

public class RoomGroupLinkedList {

	private Node head, current;
	private int totalGroups;
	
	public RoomGroupLinkedList() {
		this.head = null;
		this.current = null;
		this.totalGroups = 0;
	}
	
    public void add(RoomType roomType, int numberOfRooms) {
        
    	Node newNode = new Node(roomType, numberOfRooms);
    	this.totalGroups++;
        if (head == null) {
        	this.head 	 = newNode;
        	this.current = head;
        	
        }else {
        	this.current.next = newNode;
        	this.current = this.current.next; 
        }
        
    }
    
    public Node[] getList() {
    	Node[] roomGroupList = new Node[this.totalGroups];
    	Node roomGroup = this.head;
    	int index = 0;
    	
    	while(roomGroup != null && index < this.totalGroups) {
    		roomGroupList[index++] = roomGroup;
    		roomGroup = roomGroup.next;
    	}
    	
    	return roomGroupList;
    }
    
    public int getTotalElements() {
    	return this.totalGroups;
    }
 	
}
