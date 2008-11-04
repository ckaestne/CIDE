package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class funlhsR1 extends funlhsR {
  public funlhsR1(block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public funlhsR1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new funlhsR1(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
}
