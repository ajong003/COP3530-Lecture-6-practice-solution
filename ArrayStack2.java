public class ArrayStack2 implements LIFOStack{

    Object[] objectArray;
    public ArrayStack2(){
        objectArray= new Object[0];

    }

    @Override
    public void push(Object element) {
        if(objectArray.length==0){
            Object[] newArray = new Object[1];
            newArray[0]=element;
            objectArray=newArray;
            return;
        }
        else{
            Object[] tempArray = new Object[objectArray.length+1];
            for(int i=0;i<objectArray.length;i++){
                tempArray[i+1]=objectArray[i];
            }
            tempArray[0]=element;
            objectArray=tempArray;
        }


    }

    @Override
    public boolean isEmpty() {
        if (objectArray.length==0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Object pop() {
        Object returbObject=objectArray[0];
        Object[] newArray = new Object[objectArray.length-1];
        for(int i=1;i<objectArray.length;i++){
            newArray[i-1]=objectArray[i];
        }
        objectArray=newArray;
        return returbObject;
    }

    @Override
    public Object peek() {
        return objectArray[0];
    }
}
