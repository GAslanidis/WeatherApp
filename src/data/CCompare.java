package data;

// This is the default class that an array will use for its comparison object (see CArray.java, line 47)
//  
// If you leave the default comparison object, make sure that the data type of 
// the array elements (used here instead of T) is implementing the 
// generic interface Comparable<T>
//
// [OBJECT ORIENTED]: Inheritance
// [OBJECT ORIENTED]: Polymorphism with methods that are overriden (virtual methods).
// [OBJECT ORIENTED]: Abstraction with interfaces
public class CCompare<T> extends CComparison
{
    //--------------------------------------------------------------------------
    // Overriding the default functionality with our custom code.
    // If w the method Equals() it will use the overriden functionality
    @Override
    public int Compare(Object p_oItem1, Object p_oItem2)
    {
        Comparable<T> iItem1 = (Comparable<T>)p_oItem1;
        T oItem2 = (T)p_oItem2;
        if (iItem1 != null)
            return iItem1.compareTo(oItem2);
        else
            return 0; // Class T does not implement Comparable<T>
    }
    //--------------------------------------------------------------------------
    
}