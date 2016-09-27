package pt.isel.ls;

public class Ints {
    public static int max(int a, int b){
        return a >= b ? a : b;
    }

    public static int indexOfBinary(int[] a, int fromIndex, int toIndex, int n) {

        if (fromIndex < 0 || toIndex >a.length)
            throw new IllegalArgumentException("Invalid limits: from(" + fromIndex + ")" + "to(" + toIndex +")");

        if (fromIndex > toIndex)
            throw new IllegalArgumentException("from(" + fromIndex + ") > to(" + toIndex + ")");

        int low = fromIndex;
        int high = toIndex - 1;
        int mid;

        while(low <= high){
            mid = (high + low )/ 2 ;
            if(n > a[mid]) low = mid + 1;
            else if(n < a[mid]) high = mid - 1;
            else return mid;
        }
        return -1;
    }
}
