// This solutions uses Trie based approach where we store the possible strings with prefix until that point on all nodes
// We also use a map to store the frequenceies
// Whenever a new string is added, we rearrange the list with top 3 elements based on frequencies
class AutocompleteSystem {

    TrieNode root;
    Map<String, Integer> map;
    String search = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap();

        for(int i=0;i<sentences.length;i++) {
            if(!map.containsKey(sentences[i])) {
                map.put(sentences[i], 0);
            }
            map.put(sentences[i], map.get(sentences[i])+times[i]);
            insert(sentences[i], '#');
        }
    }

    private List<String> insert(String s, char p) {
        TrieNode curr = root;
        for(char c:s.toCharArray()) {
            if(curr.children[c-' ']==null) {
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c-' '];
            if(p=='#') {
                curr.searchResults.remove(s);
                curr.searchResults.add(s);
                Collections.sort(curr.searchResults, (a,b)-> {
                    if(map.get(a)!=map.get(b)) {
                        return map.get(b)-map.get(a);
                    } else {
                        return a.compareTo(b);
                    }
                });
                if(curr.searchResults.size()>3) {
                    curr.searchResults.remove(curr.searchResults.size()-1);
                }
            }
        }
        return curr.searchResults;
    }

    private List<String> fetchRecommendations(String s) {
        TrieNode curr = root;
        for(char c:s.toCharArray()) {
            if(curr.children[c-' ']==null) {
                return new ArrayList();
            }
            curr = curr.children[c-' '];
        }
        return curr.searchResults;
    }

    public List<String> input(char c) {
        if(c=='#') {
            map.put(search, map.getOrDefault(search,0)+1);
            insert(search, c);
            search=new String("");
            return new ArrayList();
        }
        search=search+String.valueOf(c);
        return fetchRecommendations(search);
    }
}

class TrieNode {
    TrieNode[] children;
    List<String> searchResults;

    public TrieNode() {
        children = new TrieNode[126];
        searchResults = new ArrayList();
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

