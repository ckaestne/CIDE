package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier5 extends TypeSpecifier {
  public TypeSpecifier5(ASTStringNode long_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("long_kw", long_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLong_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("long_kw")).getValue();
  }
}
