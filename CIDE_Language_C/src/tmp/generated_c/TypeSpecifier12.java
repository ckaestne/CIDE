package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier12 extends TypeSpecifier {
  public TypeSpecifier12(TypedefName typedefName, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypedefName>("typedefName", typedefName)
    }, firstToken, lastToken);
  }
  public TypeSpecifier12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier12(cloneProperties(),firstToken,lastToken);
  }
  public TypedefName getTypedefName() {
    return ((PropertyOne<TypedefName>)getProperty("typedefName")).getValue();
  }
}
