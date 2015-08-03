package com.ttc.util.ws;

import java.util.Comparator;

import com.ttc.ch2.api.ccapi.v3.Message;
import com.ttc.ch2.api.ccapi.v3.MessageType;

public class MessageComparator implements Comparator<Message> {
    MessageTypeComparator messageTypeComparator = new MessageTypeComparator();
    
    @Override
    public int compare(Message o1, Message o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            }
            return -1;
        }
        
        if (o2 == null) {
            return 1;
        }
        
        int result = messageTypeComparator.compare(o1.getMessageType(), o2.getMessageType());
        return result;
    }
}

class MessageTypeComparator implements Comparator<MessageType> {
//    MessageSubjectComparator messageSubjectComparator = new MessageSubjectComparator();
    StringComparator stringComparator = new StringComparator();
    
    @Override
        public int compare(MessageType o1, MessageType o2) {
            if (o1 == null) {
                if (o2 == null) {
                    return 0;
                }
                return -1;
            }
            
            if (o2 == null) {
                return 1;
            }

            int result = stringComparator.compare(o1.getCode(), o2.getCode());
            if (result != 0) {
                return result;
            }
            
            result = stringComparator.compare(o1.getDescription(), o2.getDescription());
            if (result != 0) {
                return result;
            }

        return 0;
    }
        
    }
//        
//        class MessageSubjectComparator implements Comparator<MessageSubject> {
//            StringComparator stringComparator = new StringComparator();
//            
//            @Override
//            public int compare(MessageSubject o1, MessageSubject o2) {
//                public int compare(Message o1, Message o2) {
//                    if (o1 == null) {
//                        if (o2 == null) {
//                            return 0;
//                        }
//                        return -1;
//                    }
//                    
//                    if (o2 == null) {
//                        return 1;
//                    }
//                    
//                    stringComparator.compare(o1.g, o2)
//                    // TODO Auto-generated method stub
//                    return 0;
//                }
//            }


class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            }
            return -1;
        }
        
        if (o2 == null) {
            return 1;
        }
        
        return o1.compareTo(o2);
    
    }
}
