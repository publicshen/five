package com.x.redis.model;

public class User {

    private long id;
    private String name;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\":\"").append(id).append("\",\"name\":\"").append(name).append("\"}");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		int i = 5 << 2;
		System.out.println(i);
	}
	
}