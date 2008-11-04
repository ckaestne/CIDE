package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ebnf extends GenASTNode {
  public ebnf(block block, blockModifier blockModifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block),
      new PropertyZeroOrOne<blockModifier>("blockModifier", blockModifier)
    }, firstToken, lastToken);
  }
  public ebnf(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ebnf(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
  public blockModifier getBlockModifier() {
    return ((PropertyZeroOrOne<blockModifier>)getProperty("blockModifier")).getValue();
  }
}
