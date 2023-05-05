package org.alexdev.havana.messages.outgoing.messenger;

import org.alexdev.havana.game.messenger.MessengerCategory;
import org.alexdev.havana.game.messenger.MessengerUser;
import org.alexdev.havana.game.player.Player;
import org.alexdev.havana.messages.types.MessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

public class ADD_BUDDY extends MessageComposer {
    private final MessengerUser friend;
    private final Player player;

    public ADD_BUDDY(Player player, MessengerUser friend) {
        this.friend = friend;
        this.player = player;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.player.getMessenger().getCategories().size());

        for (var category : this.player.getMessenger().getCategories()) {
            response.writeInt(category.getId());
            response.writeString(category.getName());
        }

        response.writeInt(1);
        response.writeInt(1);

        this.friend.serialise(this.player, response);
    }

    @Override
    public short getHeader() {
        return 13;
    }
}
