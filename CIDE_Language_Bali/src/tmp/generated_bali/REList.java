package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class REList extends GenASTNode {
  public REList(ArrayList<RegexBlock> regexBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<RegexBlock>("regexBlock", regexBlock)
    }, firstToken, lastToken);
  }
  public REList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new REList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<RegexBlock> getRegexBlock() {
    return ((PropertyList<RegexBlock>)getProperty("regexBlock")).getValue();
  }
}
