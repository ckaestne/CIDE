package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier11 extends TypeSpecifier {
  public TypeSpecifier11(EnumSpecifier enumSpecifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EnumSpecifier>("enumSpecifier", enumSpecifier)
    }, firstToken, lastToken);
  }
  public TypeSpecifier11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier11(cloneProperties(),firstToken,lastToken);
  }
  public EnumSpecifier getEnumSpecifier() {
    return ((PropertyOne<EnumSpecifier>)getProperty("enumSpecifier")).getValue();
  }
}
