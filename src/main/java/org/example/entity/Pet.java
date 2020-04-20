package org.example.entity;

public class Pet {
    private int id;
    private String name;
    private int age;
    private String breed;

    public Pet() {
    }

    public Pet(int id, String name, int age, String breed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return "image_url";
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
