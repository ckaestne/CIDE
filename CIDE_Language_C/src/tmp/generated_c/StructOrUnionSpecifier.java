package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructOrUnionSpecifier extends GenASTNode {
  public StructOrUnionSpecifier(StructOrUnion structOrUnion, StructOrUnionSpecifierInner structOrUnionSpecifierInner, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StructOrUnion>("structOrUnion", structOrUnion),
      new PropertyOne<StructOrUnionSpecifierInner>("structOrUnionSpecifierInner", structOrUnionSpecifierInner)
    }, firstToken, lastToken);
  }
  public StructOrUnionSpecifier(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructOrUnionSpecifier(cloneProperties(),firstToken,lastToken);
  }
  public StructOrUnion getStructOrUnion() {
    return ((PropertyOne<StructOrUnion>)getProperty("structOrUnion")).getValue();
  }
  public StructOrUnionSpecifierInner getStructOrUnionSpecifierInner() {
    return ((PropertyOne<StructOrUnionSpecifierInner>)getProperty("structOrUnionSpecifierInner")).getValue();
  }
}
