package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class unsafe_statement extends GenASTNode {
  public unsafe_statement(block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public unsafe_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new unsafe_statement(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
}
