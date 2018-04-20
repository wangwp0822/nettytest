package nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

public class NettyServerHandler extends ChannelHandlerAdapter{
    public void channelRead(ChannelHandlerContext ctx, Object msg) {  
        ByteBuf buf = (ByteBuf) msg;  
        String recieved = getMessage(buf);  
        System.out.println("���������յ���Ϣ��" + recieved);  
          
        try {  
            ctx.writeAndFlush(getSendByteBuf("APPLE"));  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
    }  
	  
    /* 
     * ��ByteBuf�л�ȡ��Ϣ ʹ��UTF-8���뷵�� 
     */  
    private String getMessage(ByteBuf buf) {  
        byte[] con = new byte[buf.readableBytes()];  
        buf.readBytes(con);  
        try {  
            return new String(con, "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
	      
    private ByteBuf getSendByteBuf(String message) throws UnsupportedEncodingException {  
        byte[] req = message.getBytes("UTF-8");  
        ByteBuf pingMessage = Unpooled.buffer();  
        pingMessage.writeBytes(req);  
  
        return pingMessage;  
    }  
}
