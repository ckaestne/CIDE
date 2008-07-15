package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Constant2 extends Constant {
  public Constant2(ASTStringNode floating_point_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("floating_point_literal", floating_point_literal)
    }, firstToken, lastToken);
  }
  public Constant2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Constant2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloating_point_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("floating_point_literal")).getValue();
  }
}
