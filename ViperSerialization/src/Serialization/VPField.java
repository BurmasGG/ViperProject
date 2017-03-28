package Serialization;
 
import static Serialization.SerializationWriter.*;

public class VPField 
{
	public static final byte CONTAINER_TYPE = ContainerType.FIELD;
	public short nameLength;
	public byte[] name;
	public byte type;
	public byte[] data;
	
	private VPField()
	{
		
	}
	
	public void setName(String name)
	{
		nameLength = (short)name.length();
		this.name = name.getBytes();
	}
	
	public String getName() 
	{
		return new String(name, 0, nameLength);
	}
	
	public int getBytes(byte[] dest, int pointer)
	{
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, type);
		pointer = writeBytes(dest, pointer, data);
		return pointer;
	}
	
	public int getSize()
	{
		return 1 + 2 + nameLength + 1 + data.length;
	}
	
	public static VPField Byte(String name, byte value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.BYTE;
		field.data = new byte[Type.getSize(Type.BYTE)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Short(String name, short value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.SHORT;
		field.data = new byte[Type.getSize(Type.SHORT)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Char(String name, char value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.CHAR;
		field.data = new byte[Type.getSize(Type.CHAR)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Integer(String name, int value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.INT;
		field.data = new byte[Type.getSize(Type.INT)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Long(String name, long value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.LONG;
		field.data = new byte[Type.getSize(Type.LONG)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Float(String name, float value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.FLOAT;
		field.data = new byte[Type.getSize(Type.FLOAT)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Double(String name, double value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.DOUBLE;
		field.data = new byte[Type.getSize(Type.DOUBLE)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Boolean(String name, boolean value)
	{
		VPField field = new VPField();
		field.setName(name);
		field.type = Type.BOOLEAN;
		field.data = new byte[Type.getSize(Type.BOOLEAN)];
		writeBytes(field.data, 0, value);
		return field;
	}
	
	public static VPField Deserialize(byte[] data, int pointer)
	{
		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);
		
		VPField result = new VPField();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.type = data[pointer++];
		
		result.data = new byte[Type.getSize(result.type)];
		readBytes(data, pointer, result.data);
		pointer += Type.getSize(result.type);
		return result;
	}
}








