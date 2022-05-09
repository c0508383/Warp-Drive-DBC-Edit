package cr0s.warpdrive.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cr0s.warpdrive.client.ClientProxy;
import io.netty.buffer.ByteBuf;

public class MessageClientBreath implements IMessage, IMessageHandler<MessageClientBreath, IMessage> {
    private byte dbcRace;
    private float airRatio;

    public MessageClientBreath() {

    }

    public MessageClientBreath(final byte dbcRace, final float airRatio) {
        this.dbcRace = dbcRace;
        this.airRatio = airRatio;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        dbcRace = buf.readByte();
        airRatio = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(dbcRace);
        buf.writeFloat(airRatio);
    }

    @SideOnly(Side.CLIENT)
    public void handle() {
        ClientProxy.dbcRace = dbcRace;
        ClientProxy.airRatio = airRatio;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MessageClientBreath message, MessageContext ctx) {
        message.handle();

        return null;
    }
}
