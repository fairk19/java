package messageSystem;

import accountService.Address;
import base.Frontend;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class MsgUpdateUserId extends MsgToFrontend {

        private String sessionId;
        private Long id;

        public MsgUpdateUserId(Address from, Address to, String sessionId, Long id) {
            super(from, to);
            this.sessionId = sessionId;
            this.id = id;
        }

        void exec(Frontend frontend) {
            frontend.setId(sessionId, id);
        }
}