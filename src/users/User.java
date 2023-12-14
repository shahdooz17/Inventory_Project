package users;
import Database.InterFace;

import javax.lang.model.type.IntersectionType;
import java.io.IOException;
import java.util.InputMismatchException;
public abstract class User implements InterFace {
    private String id;
    private String name;
    private int age;

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) throws InputMismatchException {
        if(!name.matches("^[a-zA-Z]+$"))
            throw new InputMismatchException("Invalid name");
        this.name = name;
    }

    public void setAge(int age) throws InputMismatchException {
        if(age <= 0 || age >100)
            throw new InputMismatchException("Invalid age");
        this.age = age;
    }
}
