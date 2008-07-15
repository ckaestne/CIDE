package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Constant1 extends Constant {
  public Constant1(ASTStringNode integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("integer_literal", integer_literal)
    }, firstToken, lastToken);
  }
  public Constant1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Constant1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
}
