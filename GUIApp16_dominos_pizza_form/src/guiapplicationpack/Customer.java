package guiapplicationpack;

public class Customer 
{
    private String custName, custPhone, crustType, size, delivery;
    private boolean[] toppings;
    
    public Customer()
    {
        custName = "";
        custPhone = "";
    }
    public Customer(String name, String phone, String size, String type, String delivery, boolean[] topping)
    {
        custName = name;
        custPhone = phone;
        this.crustType = type;
        this.size = size;
        this.delivery = delivery;
        toppings = topping;
    }
    public String getName(){
        return custName;
    }
    public String getPhone(){
        return custPhone;
    }
    public String getCrust(){
        return crustType;
    }
    public String getSize(){
        return size;
    }
    public String getDelivery(){
        return delivery;
    }
    public boolean[] getToppings(){
        return toppings;
    }
}
