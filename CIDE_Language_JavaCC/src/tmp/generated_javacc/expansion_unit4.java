package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unit4 extends expansion_unit {
  public expansion_unit4(expansion_choices expansion_choices1, ArrayList<CatchPart> catchPart, Block block1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expansion_choices>("expansion_choices1", expansion_choices1),
      new PropertyZeroOrMore<CatchPart>("catchPart", catchPart),
      new PropertyZeroOrOne<Block>("block1", block1)
    }, firstToken, lastToken);
  }
  public expansion_unit4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unit4(cloneProperties(),firstToken,lastToken);
  }
  public expansion_choices getExpansion_choices1() {
    return ((PropertyOne<expansion_choices>)getProperty("expansion_choices1")).getValue();
  }
  public ArrayList<CatchPart> getCatchPart() {
    return ((PropertyZeroOrMore<CatchPart>)getProperty("catchPart")).getValue();
  }
  public Block getBlock1() {
    return ((PropertyZeroOrOne<Block>)getProperty("block1")).getValue();
  }
}
