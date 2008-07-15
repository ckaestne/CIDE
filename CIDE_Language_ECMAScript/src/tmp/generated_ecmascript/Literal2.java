package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal2 extends Literal {
  public Literal2(ASTStringNode hex_integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("hex_integer_literal", hex_integer_literal)
    }, firstToken, lastToken);
  }
  public Literal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getHex_integer_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("hex_integer_literal")).getValue();
  }
}
