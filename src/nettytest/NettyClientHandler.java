package nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

public class NettyClientHandler extends ChannelHandlerAdapter {
	private  ByteBuf firstMessage;  
    
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        byte[] data = "������������һ��APPLE".getBytes();  
        firstMessage=Unpooled.buffer();  
        firstMessage.writeBytes(data);  
          
        ctx.writeAndFlush(firstMessage);  
    }  
       
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        ByteBuf buf = (ByteBuf) msg;  
	    String rev = getMessage(buf);  
	    System.out.println("�ͻ����յ�����������:" + rev);  
           
    }  
       
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
}
