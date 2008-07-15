package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class checked_statement extends GenASTNode {
  public checked_statement(block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public checked_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new checked_statement(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
}
