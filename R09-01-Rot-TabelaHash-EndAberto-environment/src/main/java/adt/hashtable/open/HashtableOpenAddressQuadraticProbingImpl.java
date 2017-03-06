package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (element == null || search(element) != null)
         return;
      if (isFull())
         throw new HashtableOverflowException();

      int i = 0;

      while (i < table.length) {
         int index = getHashFunction().hash(element, i);

         if (table[index] == null || table[index].equals(deletedElement)) {
            table[index] = element;
            break;
         }
         i++;
         COLLISIONS++;
      }
      elements++;
   }

   @Override
   public void remove(T element) {
      if (element == null || isEmpty())
         return;

      int i = 0;

      while (i < table.length) {
         int index = getHashFunction().hash(element, i);
         if (table[index] == null)
            break;
         if (table[index].equals(element)) {
            table[index] = deletedElement;
            elements--;
            break;
         }
         i++;
      }
   }

   @Override
   public T search(T element) {
      if (indexOf(element) == -1)
         return null;
      return element;
   }

   @Override
   public int indexOf(T element) {
      if (element == null)
         return -1;

      int i = 0;

      while (i < table.length) {
         int index = getHashFunction().hash(element, i);
         if (table[index] == null)
            return -1;
         if (table[index].equals(element))
            return index;

         i++;
      }
      return -1;
   }

   public HashFunctionQuadraticProbing<T> getHashFunction() {
      return (HashFunctionQuadraticProbing<T>) this.hashFunction;
   }
}
