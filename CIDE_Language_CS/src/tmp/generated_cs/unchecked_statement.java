package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class unchecked_statement extends GenASTNode {
  public unchecked_statement(block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public unchecked_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new unchecked_statement(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
}
