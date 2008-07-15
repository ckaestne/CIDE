package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class operator_declaration1 extends operator_declaration {
  public operator_declaration1(unary_operator_declaration unary_operator_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<unary_operator_declaration>("unary_operator_declaration", unary_operator_declaration)
    }, firstToken, lastToken);
  }
  public operator_declaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new operator_declaration1(cloneProperties(),firstToken,lastToken);
  }
  public unary_operator_declaration getUnary_operator_declaration() {
    return ((PropertyOne<unary_operator_declaration>)getProperty("unary_operator_declaration")).getValue();
  }
}
