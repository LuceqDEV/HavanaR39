/**
 * Author: Jeppe#9821 -- github.com/JeppsonDev
 */

package org.alexdev.havana.messages.outgoing.user.badges;

import org.alexdev.havana.game.badges.Badge;
import org.alexdev.havana.messages.types.PlayerMessageComposer;
import org.alexdev.havana.server.netty.streams.NettyResponse;

import java.util.List;

public class USERBADGE_FLASH  extends PlayerMessageComposer {
    private final int userId;
    private final List<Badge> equippedBadges;

    public USERBADGE_FLASH(int userId, List<Badge> equippedBadges) {
        this.userId = userId;
        this.equippedBadges = equippedBadges;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.userId);
        response.writeInt(this.equippedBadges.size());

        for (Badge badge : this.equippedBadges) {
            response.writeInt(badge.getSlotId());
            response.writeString(badge.getBadgeCode());
        }
    }

    @Override
    public short getHeader() {
        return 228; // "Cd"
    }
}
