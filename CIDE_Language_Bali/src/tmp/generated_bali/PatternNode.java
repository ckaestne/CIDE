package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PatternNode extends PrimitiveRewrite {
  public PatternNode(Pattern pattern, ClassName className, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Pattern>("pattern", pattern),
      new PropertyZeroOrOne<ClassName>("className", className)
    }, firstToken, lastToken);
  }
  public PatternNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PatternNode(cloneProperties(),firstToken,lastToken);
  }
  public Pattern getPattern() {
    return ((PropertyZeroOrOne<Pattern>)getProperty("pattern")).getValue();
  }
  public ClassName getClassName() {
    return ((PropertyZeroOrOne<ClassName>)getProperty("className")).getValue();
  }
}
