package WordConversionTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import WordConversion.Branch;
import WordConversion.WordConversion;

public class WordConversionTest {

	private static final String DICT_TXT = "dict.txt";

	@Test
	public void findPath() throws IOException {
		WordConversion conv = new WordConversion(DICT_TXT);
		String sourceFilePath = "source.txt";
		List<String> findPath = conv.findPath(sourceFilePath);
		for (String string : findPath) {
			System.out.println(string);
		}
		String[] truePath = {"����", "����", "����", "����"};
		Assert.assertArrayEquals(truePath, findPath.toArray());
	}

	@Test
	public void getNeighbours() throws IOException {
		WordConversion conv = new WordConversion(DICT_TXT);
		
		assertTrue(conv.getDictionary().contains("����"));
		assertTrue(conv.getDictionary().contains("����"));
		
		List<Branch<String>> actual = conv.getNeighbours(new Branch<String>("����", null));
		
		assertEquals(2, actual.size());
		assertEquals("����", actual.get(0).getData());
		assertEquals("����", actual.get(1).getData());
		
		assertTrue(conv.getUsedWords().contains("����"));
		assertTrue(conv.getUsedWords().contains("����"));
	}

	@Test
	public void dist() throws IOException {
		WordConversion conv = new WordConversion(DICT_TXT);
		Assert.assertEquals(3, conv.dist("����", "����"));
		Assert.assertEquals(2, conv.dist("����", "����"));
		Assert.assertEquals(1, conv.dist("����", "����"));
		Assert.assertEquals(0, conv.dist("����", "����"));
	}
}
