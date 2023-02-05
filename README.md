# Lecture 6: Abstract Data Type - Stack
The github for this page is:

https://github.com/itamames/Lecture6

## Stack Model  

https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html

Definition: a stack is a list with the restriction that insertions and deletions can be performed in only one position, namely, the end of the list, called the top. 

Stacks are sometimes known as LIFO - (L)ast (I)n, (F)irst (O)ut lists.

Operations:
* push – insert (on the top)
* pop - delete and retrieve the most recently inserted element

![Insert into Linkedlist](images/Stack.png)

## Implementation of Stacks

ArrayList and LinkedList support stack operations 

### Linked List implementation of Stacks
* Singly linked list.
* A top operation merely examines the element at the front of the list, returning its value.
* We perform a pop by deleting the element at the front of the list.

### Arraylist Implementation of Stacks
* ArrayList
* topOfStack, which is −1 for an empty stack (this is how an empty stack is initialized).
* To push some element x onto the stack, we increment topOfStack and then set theArray [topOfStack] = x. 
* To pop, we set the return value to theArray [topOfStack] and then decrement topOfStack.
* Notice that pop and push operations are performed in not only constant time, but very fast constant time.

```java
import java.util.ArrayList;
import java.util.List;
public class Stack {
	@Override
	public String toString() {
		return "Stack [elements=" + elements + "]";
	}
	private List<String> elements = new ArrayList<>();
	public String peek() {
		if (elements.isEmpty()) {
			return null;
		}
		return elements.get(elements.size() - 1);
	}
	
	public String pop() {
		if (elements.isEmpty()) {
			return null;
		}
		String top = elements.get(elements.size() - 1);
		elements.remove(elements.size() - 1);
		return top;
	}
	public void push(String element) {
		elements.add(element);
	}
	public int size() {
		return elements.size();
	}
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	public static void main(String[] args) {
		
		Stack stack = new Stack();
		System.out.println("Is Stack Empty:"+stack.isEmpty());
		stack.push("Gyan");
		stack.push("Vivek");
		stack.push("Rochit");
		stack.push("Panda");
		System.out.println("Is Stack Empty:"+stack.isEmpty());
		System.out.println(stack);
		System.out.println("Stack Size:"+stack.size());
		System.out.println("Peek Top Element:"+stack.peek());
		System.out.println("After peek:"+stack);
		System.out.println("Pop Top Element:"+stack.pop());
		System.out.println("After pop:"+stack);
		System.out.println("Stack Size now:"+stack.size());
		
	}
}
```
Alternately use the java.util implementation.
```java
import java.io.*;
import java.util.*;
  
class Test
{   
    // Pushing element on the top of the stack
    static void stack_push(Stack<Integer> stack)
    {
        for(int i = 0; i < 5; i++)
        {
            stack.push(i);
        }
    }
      
    // Popping element from the top of the stack
    static void stack_pop(Stack<Integer> stack)
    {
        System.out.println("Pop Operation:");
  
        for(int i = 0; i < 5; i++)
        {
            Integer y = (Integer) stack.pop();
            System.out.println(y);
        }
    }
  
    // Displaying element on the top of the stack
    static void stack_peek(Stack<Integer> stack)
    {
        Integer element = (Integer) stack.peek();
        System.out.println("Element on stack top: " + element);
    }
      
    // Searching element in the stack
    static void stack_search(Stack<Integer> stack, int element)
    {
        Integer pos = (Integer) stack.search(element);
  
        if(pos == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element is found at position: " + pos);
    }
  
  
    public static void main (String[] args)
    {
        Stack<Integer> stack = new Stack<Integer>();
  
        stack_push(stack);
        stack_pop(stack);
        stack_push(stack);
        stack_peek(stack);
        stack_search(stack, 2);
        stack_search(stack, 6);
    }
}
```


# Applications of the Stacks

## Balancing Symbols

Compilers check programs for syntax errors, but frequently a lack of one symbol (such as a missing brace or comment starter) will cause the compiler to spill out a hundred lines of diagnostics without identifying the real error. 

A useful tool in this situation is a program that checks whether everything is balanced.

IE: Every right brace, bracket, and parenthesis must correspond to its left counterpart.

The sequence [ ( ) ] is legal, but [ ( ] ) is wrong. 

### Example:

For simplicity, we will (only) just check for balancing of parentheses (), brackets [], and braces {}, and ignore any other character that appears.

### Algorithm: 
* Make an empty stack. Read characters until end of file.
* If the character is an opening symbol: (  {  [
    * push it onto the stack. 
* If it is a closing symbol: ) } ] 
    * if the stack is empty report  and error. 
    * Otherwise, pop the stack. 
        * If the symbol popped is not the corresponding opening symbol, then report an error. 
* At end of file, if the stack is not empty report an error.

## Postfix Expressions
Suppose we have a pocket calculator and would like to compute the cost of a shopping trip. 

The list of items are 4.99, 5.99, and 6.99 and suppose that the first and the last items are taxable and the second is not.

The sequence 4.99 ∗ 1.06 + 5.99 + 6.99 ∗ 1.06 would give:
* the correct answer (18.69) on a scientific calculator,
* the wrong answer (19.37) on a simple calculator.

A typical evaluation sequence for this example might be to:
* multiply 4.99 and 1.06 saving this answer as A1, 
* add 5.99 and A1, saving the result in A1, 
* multiply 6.99 and 1.06, saving the answer in A2,
* add A1 and A2, leaving the final answer in A1. 

We can write this sequence of operations as follows:
* 	4.99 1.06 ∗ 5.99 + 6.99 1.06 ∗ +

This notation is known as Postfix or Reverse Polish Notation (RPN) and is evaluated exactly as we have described above.

### How to evaluate this sequence? 

The easiest way to do this is to use a stack!!! 

### Algorithm:
* When a number is seen, it is pushed onto the stack
* when an operator is seen, the operator is applied to the two numbers (symbols) that are popped from the stack, and the result is pushed onto the stack. 

Example:     6 5 2 3 + 8 ∗ + 3 + ∗
* when a number is seen, it is pushed onto the stack;

![Insert into Linkedlist](images/Stack1.png)

* when an operator is seen, the operator is applied to the two numbers (symbols) that are popped from the stack, and the result is pushed onto the stack
    * Next a ‘+’ is read, so 3 and 2 are popped from the stack and their sum, 5, is pushed.

![Insert into Linkedlist](images/Stack2.png)

* Next 8 is pushed

![Insert into Linkedlist](images/Stack4.png)

* Now a ‘∗’ is seen, so 8 and 5 are popped and 5 ∗ 8 = 40 is pushed.

![Insert into Linkedlist](images/Stack5.png)

* Next a ‘+’ is seen, so 40 and 5 are popped and 5 + 40 = 45 is pushed.

![Insert into Linkedlist](images/Stack7.png)

* Now, 3 is pushed.

![Insert into Linkedlist](images/Stack8.png)

* Next ‘+’ pops 3 and 45 and pushes 45 + 3 = 48.

![Insert into Linkedlist](images/Stack10.png)

* Finally, a ‘∗’ is seen and 48 and 6 are popped; the result, 6 ∗ 48 = 288, is pushed.

![Insert into Linkedlist](images/Stack11.png)


# Class Practice


In this lab, you will implement a new version of the ArrayStack class and then test it . Remember to work with a partner or 2.



## EXERCISES
Clone the github link at the start of the class.

1) Create a new class named ArrayStack2 that implements a stack where the top of the stack is always stored in position 0 of the array. 
    * When an element is pushed on this stack, the elements on the stack must be shifted to make room for the new element. 
    * Likewise, when an element is popped from this stack, the elements on the stack must be shifted the opposite direction to fill in the gap left by the removed element. 
    * Your class should implement the LIFOStack interface so it must include all of the required methods specified by that interface in the project.

2) Test your ArrayStack2 class by changing the PostfixEvaluator class so that it uses a stack of type ArrayStack2 instead of ListStack.

3) Create a new class Calculator that contains a main method that asks the user for an expression in infix notation and computes its value by first converting it to postfix and then evaluating its value. 
    * The infix expression must be input with spaces between each token. (This should not require a lot of code... THINK.)

4) The postfix evaluator and infix-to-postfix generators can handle input of multiple digit positive integers as operands. 
    * How does it know if a token is an integer? 
    * What happens if someone enters a token like 7UP that looks like an integer initially but really isn't? 
    * Modify the infix-to-postfix converter to deal with this case by throwing a syntax error exception. 
    * Catch this in your calculator and ask the user to input a valid infix expression until the user finally does.
