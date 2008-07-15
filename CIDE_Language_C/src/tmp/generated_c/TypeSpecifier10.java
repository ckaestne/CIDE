package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier10 extends TypeSpecifier {
  public TypeSpecifier10(StructOrUnionSpecifier structOrUnionSpecifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StructOrUnionSpecifier>("structOrUnionSpecifier", structOrUnionSpecifier)
    }, firstToken, lastToken);
  }
  public TypeSpecifier10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier10(cloneProperties(),firstToken,lastToken);
  }
  public StructOrUnionSpecifier getStructOrUnionSpecifier() {
    return ((PropertyOne<StructOrUnionSpecifier>)getProperty("structOrUnionSpecifier")).getValue();
  }
}
