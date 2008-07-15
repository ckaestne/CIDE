package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class catch_clauseEnd2 extends catch_clauseEnd {
  public catch_clauseEnd2(block block1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<block>("block1", block1)
    }, firstToken, lastToken);
  }
  public catch_clauseEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new catch_clauseEnd2(cloneProperties(),firstToken,lastToken);
  }
  public block getBlock1() {
    return ((PropertyOne<block>)getProperty("block1")).getValue();
  }
}
