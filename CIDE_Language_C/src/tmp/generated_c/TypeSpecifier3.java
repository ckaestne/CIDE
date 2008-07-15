package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier3 extends TypeSpecifier {
  public TypeSpecifier3(ASTStringNode short_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("short_kw", short_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getShort_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("short_kw")).getValue();
  }
}
