package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RegexBlock extends GenASTNode {
  public RegexBlock(Regex regex, Block block, NextState nextState, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Regex>("regex", regex),
      new PropertyZeroOrOne<Block>("block", block),
      new PropertyZeroOrOne<NextState>("nextState", nextState)
    }, firstToken, lastToken);
  }
  public RegexBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RegexBlock(cloneProperties(),firstToken,lastToken);
  }
  public Regex getRegex() {
    return ((PropertyOne<Regex>)getProperty("regex")).getValue();
  }
  public Block getBlock() {
    return ((PropertyZeroOrOne<Block>)getProperty("block")).getValue();
  }
  public NextState getNextState() {
    return ((PropertyZeroOrOne<NextState>)getProperty("nextState")).getValue();
  }
}
