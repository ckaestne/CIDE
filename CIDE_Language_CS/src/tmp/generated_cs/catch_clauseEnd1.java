package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class catch_clauseEnd1 extends catch_clauseEnd {
  public catch_clauseEnd1(type type, identifier identifier, block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyZeroOrOne<identifier>("identifier", identifier),
      new PropertyOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public catch_clauseEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new catch_clauseEnd1(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyZeroOrOne<identifier>)getProperty("identifier")).getValue();
  }
  public block getBlock() {
    return ((PropertyOne<block>)getProperty("block")).getValue();
  }
}
