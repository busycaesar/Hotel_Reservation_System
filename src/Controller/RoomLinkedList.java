package Controller;

import Model.Room.RoomType;;

public class RoomLinkedList {
	
    private Node head, current;

    public static class Node {
    	
        private RoomType roomType;
        private int numRooms;
        private Node next;

        public Node(RoomType roomType, int numRooms) {
            this.roomType = roomType;
            this.numRooms = numRooms;
            this.next = null;
        }
        
        public Node getNext() {
        	return this.next;
        }
        
        public RoomType getType() {
        	return this.roomType;
        }
        
        public int getTotalRooms() {
        	return this.numRooms;
        }
        
    	@Override
    	public String toString() {
    	    return this.roomType.toString() + ": " + this.numRooms;
    	}
        
    }

    public RoomLinkedList() {
        this.head = null;
        this.current = this.head;
    }

    public void add(RoomType roomType, int numRooms) {
    	
        Node newNode = new Node(roomType, numRooms);
        
        if (this.head == null) {
            this.head = newNode;
            this.current = this.head;
        } else {
        	this.current.next = newNode;
        	this.current = this.current.next;
        }
        
    }
    
    public Node getHead() {
    	return this.head;
    }
    
    public Node getAt(int i) {
    	int index = 0;
    	Node currentNode = this.head;
    	while(index < i && currentNode != null) {
    		currentNode = currentNode.next;
    		index++;
    	}
    	return currentNode;
    }

}
