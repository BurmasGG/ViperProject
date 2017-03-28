package Serialization;

import static Serialization.SerializationWriter.*;
import java.util.ArrayList;
import java.util.List;

public class VPObject 
{
	public static final byte CONTAINER_TYPE = ContainerType.OBJECT;
	public short nameLength;
	public byte[] name;
	private int size = 1 + 2 + 4 + 2 + 2 + 2;
	private short fieldCount;
	public List<VPField> fields = new ArrayList<VPField>();
	private short stringCount;
	public List<VPString> strings = new ArrayList<VPString>();
	private short arrayCount;
	public List<VPArray> arrays = new ArrayList<VPArray>();
	
	private static final int sizeOffset = 1 + 2 + 4;
	
	private VPObject()
	{
		
	}
	
	public VPObject(String name)
	{
		setName(name);
	}
	
	public void setName(String name)
	{
		if (this.name != null)
			size -= this.name.length;
		
		nameLength = (short)name.length();
		this.name = name.getBytes();
		size += nameLength;
	}
	
	public String getName() 
	{
		return new String(name, 0, nameLength);
	}

	public void addField(VPField field) 
	{
		fields.add(field);
		size += field.getSize();
		
		fieldCount = (short)fields.size();
	}
	
	public void addString(VPString string) 
	{
		strings.add(string);
		size += string.getSize();
		
		stringCount = (short)strings.size();
	}

	public void addArray(VPArray array) 
	{
		arrays.add(array);
		size += array.getSize();
		
		arrayCount = (short)arrays.size();
	}

	public int getSize() 
	{
		return size;
	}

	public int getBytes(byte[] dest, int pointer) 
	{
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, size);
		
		pointer = writeBytes(dest, pointer, fieldCount);
		for (VPField field : fields)
			pointer = field.getBytes(dest, pointer);
		
		pointer = writeBytes(dest, pointer, stringCount);
		for (VPString string : strings)
			pointer = string.getBytes(dest, pointer);
		
		pointer = writeBytes(dest, pointer, arrayCount);
		for (VPArray array : arrays)
			pointer = array.getBytes(dest, pointer);
		
		return pointer;
	}
	
	public static VPObject Deserialize(byte[] data, int pointer)
	{
		byte containerType = data[pointer++];
		assert(containerType == CONTAINER_TYPE);
		
		VPObject result = new VPObject();
		result.nameLength = readShort(data, pointer);
		pointer += 2;
		result.name = readString(data, pointer, result.nameLength).getBytes();
		pointer += result.nameLength;
		
		result.size = readInt(data, pointer);
		pointer += 4;
		
		// if we want to exit early: pointer += result.size + sizeOffset - result.nameLength;
		
		result.fieldCount = readShort(data, pointer);
		pointer += 2;
		
		for (int i = 0; i < result.fieldCount; i++)
		{
			VPField field = VPField.Deserialize(data, pointer);
			result.fields.add(field);
			pointer += field.getSize();
		}
		
		result.stringCount = readShort(data, pointer);
		pointer += 2;
		
		for (int i = 0; i < result.stringCount; i++)
		{
			VPString string = VPString.Deserialize(data, pointer);
			result.strings.add(string);
			pointer += string.getSize();
		}
		
		result.arrayCount = readShort(data, pointer);
		pointer += 2;
		
		for (int i = 0; i < result.arrayCount; i++)
		{
			VPArray array = VPArray.Deserialize(data, pointer);
			result.arrays.add(array);
			pointer += array.getSize();
		}
		return result;
	}
	
}