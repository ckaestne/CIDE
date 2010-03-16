package tmp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import tmp.generated_manifest.File;
import tmp.generated_manifest.ManifestParser;
import tmp.generated_manifest.ManifestParserTokenManager;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.gparser.Token;

public class Test {
	public static void main(String[] args) throws FileNotFoundException,
			ParseException {
		ManifestParser parser = new ManifestParser(new OffsetCharStream(
				new FileInputStream("src/tmp/MANIFEST.MF")));
		Token token = parser.token_source.getNextToken();
		while (token.kind != 0) {
			System.out.println(ManifestParserTokenManager.tokenImage[token.kind]+"["+token.kind + "] " + token.image);
			token = parser.token_source.getNextToken();
		}
		
		parser = new ManifestParser(new OffsetCharStream(
				new FileInputStream("src/tmp/MANIFEST.MF")));
		File f = parser.File();
	System.out.println(	 f.render()); 
	}
}
