from typing import List, Optional, Union, Any

all = [
    "__build__",
    "ListNode",
    "LinkedList"
]

_NODE_VAL_TYPES = (float, int, str)
NodeValue = Any
NodeValueList = Union[
    List[Optional[int]],
    List[Optional[float]],
    List[Optional[str]],
    List[int],
    List[float],
    List[str] 
]

class ListNode:
    def __init__(self, val):
        self.val = val
        self.next = None
        
    def __str__(self):
        return f"Node ({self.val})"
            
    def clone(self):
        other = ListNode(self.val)
        queue1, queue2 = [self], [other]
        while queue1 or queue2:
            node1, node2 = queue1.pop(0), queue2.pop(0)
            if node1.next is not None:
                node2.next = ListNode(node1.next.val)
                queue1.append(node1.next)
                queue2.append(node2.next)
        return other
    
class LinkedList:
    def __init__(self, node):
        self.head = node
        
    def __str__(self):
        if not self.head: return ""
        queue = [self.head]
        string = ""
        while queue:
            node = queue.pop(0)
            if node.next is not None:
                string += f"{node.val} -> "
                queue.append(node.next)
            else:
                string += f"{node.val}"
                break
        return string
    
    def __len__(self):
        length = 0
        current_node = self.head
        while current_node is not None:
            current_node = current_node.next
            length += 1
        return length
    
    def clone(self):
        head2 = self.head.clone()
        return LinkedList(head2)
    
    def search(self, idx):
        if idx < 0 or idx > len(self):
            raise Exception("insertion index exceeds the limit of the LinkedList")
        
        target_node = self.head
        while idx > 1:
            target_node = target_node.next
            idx -= 1
        return target_node
    
    def insert(self, node, idx):
        target_node = self.search(idx)
        node.next = target_node.next
        target_node.next = node
        
    def delete(self, idx):
        target_node = self.search(idx)
        if idx == 0:
           self.head = target_node.next 
        else:
            prev = self.search(idx - 1)
            prev.next = target_node.next
        target_node.next = None
        
        
def build(arr):
    if len(arr) == 0: return None
    head = ListNode(arr.pop(0))
    queue = [head]
    while arr:
        current_node = queue.pop(0)
        current_node.next = ListNode(arr.pop(0))
        queue.append(current_node.next)
    return head