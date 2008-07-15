package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal3 extends literal {
  public literal3(ASTStringNode hexadecimal_integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("hexadecimal_integer_literal", hexadecimal_integer_literal)
    }, firstToken, lastToken);
  }
  public literal3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getHexadecimal_integer_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("hexadecimal_integer_literal")).getValue();
  }
}
