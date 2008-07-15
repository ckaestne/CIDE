package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LookaheadBody1 extends LookaheadBody {
  public LookaheadBody1(ASTStringNode integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("integer_literal", integer_literal)
    }, firstToken, lastToken);
  }
  public LookaheadBody1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LookaheadBody1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
}
