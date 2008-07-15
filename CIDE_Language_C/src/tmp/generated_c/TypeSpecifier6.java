package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier6 extends TypeSpecifier {
  public TypeSpecifier6(ASTStringNode float_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("float_kw", float_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloat_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("float_kw")).getValue();
  }
}
