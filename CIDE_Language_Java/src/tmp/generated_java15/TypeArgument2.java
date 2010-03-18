package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeArgument2 extends TypeArgument {
  public TypeArgument2(WildcardBounds wildcardBounds, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<WildcardBounds>("wildcardBounds", wildcardBounds)
    }, firstToken, lastToken);
  }
  public TypeArgument2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeArgument2(cloneProperties(),firstToken,lastToken);
  }
  public WildcardBounds getWildcardBounds() {
    return ((PropertyZeroOrOne<WildcardBounds>)getProperty("wildcardBounds")).getValue();
  }
}
