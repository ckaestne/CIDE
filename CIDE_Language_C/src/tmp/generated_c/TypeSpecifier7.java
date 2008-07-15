package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier7 extends TypeSpecifier {
  public TypeSpecifier7(ASTStringNode double_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("double_kw", double_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier7(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDouble_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("double_kw")).getValue();
  }
}
