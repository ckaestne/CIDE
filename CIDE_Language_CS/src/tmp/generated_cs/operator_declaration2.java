package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class operator_declaration2 extends operator_declaration {
  public operator_declaration2(binary_operator_declaration binary_operator_declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<binary_operator_declaration>("binary_operator_declaration", binary_operator_declaration)
    }, firstToken, lastToken);
  }
  public operator_declaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new operator_declaration2(cloneProperties(),firstToken,lastToken);
  }
  public binary_operator_declaration getBinary_operator_declaration() {
    return ((PropertyOne<binary_operator_declaration>)getProperty("binary_operator_declaration")).getValue();
  }
}
