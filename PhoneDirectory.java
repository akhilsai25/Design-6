// This approach uses two data structures to get to a o(1) complexity for all the operations
// Used array list to optimize check
// Used set to optimize check function
class PhoneDirectory {

    List<Integer> list = new ArrayList();
    Set<Integer> set = new HashSet();

    public PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++) {
            list.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(list.isEmpty()) return -1;
        int val = list.remove(list.size()-1);
        set.remove(val);
        return val;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(set.contains(number)) return;
        set.add(number);
        list.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
