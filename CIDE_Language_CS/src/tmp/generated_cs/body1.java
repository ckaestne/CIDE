package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class body1 extends body {
  public body1(block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public body1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new body1(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
}
